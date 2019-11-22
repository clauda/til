package aquariums

import kotlin.reflect.full.findAnnotation

@Vegetal
class Plant {
    fun trim() {}
    fun fertilize() {}

    @get:Catcher
    val isGrowing : Boolean = true

    @set:Place
    var needsFood : Boolean = false
}

annotation class Vegetal

@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class Catcher

@Target(AnnotationTarget.PROPERTY_SETTER)
annotation class Place

fun reflections() {
    val plantClass = Plant::class

    for (annotation in plantClass.annotations) {
        println(annotation.annotationClass.simpleName)
    }

    val annotated = plantClass.findAnnotation<Vegetal>()
    annotated?.apply { println("Vegetable Found") }


}