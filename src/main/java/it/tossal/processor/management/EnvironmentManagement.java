package it.tossal.processor.management;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author <a href="https://www.linkedin.com/in/federico-tosello/">Tosello Federico</a>
 * @version 1.0.0
 * @since JAVA 17
 */
public class EnvironmentManagement {

    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

    public EnvironmentManagement(Types typeUtils, Elements elementUtils, Filer filer, Messager messager) {
        this.typeUtils = typeUtils;
        this.elementUtils = elementUtils;
        this.filer = filer;
        this.messager = messager;
    }

    public Types getTypeUtils() {
        return typeUtils;
    }

    public Elements getElementUtils() {
        return elementUtils;
    }

    public Filer getFiler() {
        return filer;
    }

    public Messager getMessager() {
        return messager;
    }
}
