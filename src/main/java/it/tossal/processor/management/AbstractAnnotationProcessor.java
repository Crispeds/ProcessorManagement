package it.tossal.processor.management;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author <a href="https://www.linkedin.com/in/federico-tosello/">Tosello Federico</a>
 * @version 1.0.0
 * @since JAVA 17
 */
public abstract class AbstractAnnotationProcessor extends AbstractProcessor {

    protected EnvironmentManagement environmentManagement;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.environmentManagement = new EnvironmentManagement(processingEnv.getTypeUtils(),processingEnv.getElementUtils(),processingEnv.getFiler(),processingEnv.getMessager());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return getSourceVersionSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return getClassSupportedAnnotationTypes().stream().sequential().map(Class::getCanonicalName).collect(Collectors.toSet());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            for (Class<? extends Annotation> annotation: getClassSupportedAnnotationTypes()){
                for (Element e : roundEnv.getElementsAnnotatedWith(annotation))
                    checkIfEligibleAndIfProcessIt(e);
            }
        }catch (IOException e){
            return false;
        }
        return true;
    }

    private void checkIfEligibleAndIfProcessIt(Element element) throws IOException {
        try {
            checkIfElementIsEligibleForProcessing(element);
            processSingleElements(element);
        }catch (IOException e){
            environmentManagement.getMessager().printMessage(Diagnostic.Kind.ERROR,"Element not eligible for processing",element);
            throw e;
        }
    }

    /**
     * @return set of the annotations classes that this processor support
     */
    protected abstract Set<Class<? extends Annotation>> getClassSupportedAnnotationTypes();

    /**
     *
     * @return Source version supported by this processor.
     */
    protected abstract SourceVersion getSourceVersionSupported();

    /**
     * Process a single element of the processor if eligible.
     * @param e element to process
     */
    protected abstract void processSingleElements(Element e);

    /**
     * Check if the element is eligible for processing and if not write a message with environmentManagement and throw IOException.
     * @param e element to check if eligible
     * @throws IOException if element is not eligible for processing
     */
    protected abstract void checkIfElementIsEligibleForProcessing(Element e) throws IOException;

}
