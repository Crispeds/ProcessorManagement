# ProcessorManagement
processor management library for simplify implementation of abstract processor for all tossal annotation processor projects.

For use it you will only to extends your abstract processor with the AbstractAnnotationProcessor class instead of AbstractProcessor.
AbstractAnnotationProcessor require you to implement in it the checkIfElementIsEligibleForProcessing method for check if the element that you want to process is eligible for processing (if not you should throw a IOException) and implement the processSingleElements method where the real element processing happens.
