import javax.annotation.processing.Processor;
import love.polardivision.annotations.TranslationProcessor;

module love.polardivision.engine {

  requires java.compiler;
  requires java.desktop;
  requires com.google.auto.service;

  provides Processor with TranslationProcessor;

}