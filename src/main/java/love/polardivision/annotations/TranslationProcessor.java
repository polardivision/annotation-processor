/*
 * Copyright 2022 @Frooastside
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package love.polardivision.annotations;

import com.google.auto.service.AutoService;
import java.awt.Desktop;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes("love.polardivision.engine.language.UsesTranslation")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class TranslationProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    System.out.println(annotations);
    boolean claimed = false;
    for (TypeElement annotationElement : annotations) {
      Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotationElement);
      switch (annotationElement.getSimpleName().toString()) {
        case "UsesTranslation":
          claimed = true;
          handleTranslation(annotatedElements);
          break;
        default:
          break;
      }
    }
    return claimed;
  }

  private void handleTranslation(Set<? extends Element> annotatedElements) {
    for (Element enclosedElement : annotatedElements) {
      for (AnnotationMirror annotation : enclosedElement.getAnnotationMirrors()) {
        System.out.println(annotation.getAnnotationType());
      }
      for (Entry<? extends ExecutableElement, ? extends AnnotationValue> entry :
          enclosedElement.getAnnotationMirrors().get(0).getElementValues().entrySet()) {
        System.out.println(entry.getKey().getSimpleName());
        System.out.println(entry.getValue().getValue());
      }
    }
  }
}
