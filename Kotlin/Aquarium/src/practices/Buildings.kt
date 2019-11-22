package practices

open class BaseBuildingMaterial() {
    open val numberNeeded: Int = 1
}

class Wood : BaseBuildingMaterial() {
    override val numberNeeded: Int = 4
}
class Brick : BaseBuildingMaterial() {
    override val numberNeeded: Int = 4
}

class Buildings<out T: BaseBuildingMaterial>(
    val buildingMaterial: T){

    val baseMaterialsNeeded : Int = 100
    val actualMaterialNeeded : Int = buildingMaterial.numberNeeded * baseMaterialsNeeded

    fun build() {
        println("$actualMaterialNeeded ${buildingMaterial::class.simpleName} required")
    }
}

fun <T: BaseBuildingMaterial> isSmall(building: Buildings<T>) {
    if (building.actualMaterialNeeded < 500 ) println("small builing")
    else println("large building")
}

//fun main(args: Array<String>) {
//    Buildings(Wood()).build()
//    isSmall(Buildings(Brick()))
//}