package aquariums.generics

open class WaterSupply(var needsProcessed: Boolean)

class TapWater : WaterSupply(true){
    fun addChemical() {
        needsProcessed = false
    }
}

class FishStoreWater : WaterSupply(false)
class LakeWater : WaterSupply(true) {
    fun filter() {
        needsProcessed = false
    }
}

class AnyAquarium<T: Any>(val waterSupply: T)

class Aquarium<out T: WaterSupply>(val waterSupply: T) {

    fun putWaterIn(cleaner: Cleaner<T>) {
        if(waterSupply.needsProcessed) { cleaner.clean(waterSupply) }
        println("adding water from $waterSupply")
    }

//    inline fun <reified R: WaterSupply> hasWaterSupplyOfType() = waterSupply is R
}

inline fun <reified R: WaterSupply> Aquarium<*>.hasWaterSupplyOfType() = waterSupply is R

interface Cleaner<in T: WaterSupply> {
    fun clean(waterSupply: T)
}

class TapWaterCleaner : Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) {
        waterSupply.addChemical()
    }
}

fun addItemTo(aquarium: Aquarium<WaterSupply>) = println("item added")

fun <T: WaterSupply> isWaterClean(aquarium: Aquarium<T>) {
    println("aquarium water is ${aquarium.waterSupply.needsProcessed}")
}

fun genericIt() {
    val aquarium = Aquarium(TapWater())
    aquarium.waterSupply.addChemical()

//    val bowl = AnyAquarium("Bowl")
//    val ghost = Aquarium(null)

    val lakeAquarium = Aquarium(LakeWater())
    lakeAquarium.waterSupply.filter()

    val cleaner = TapWaterCleaner()

    val aquaTap = Aquarium(TapWater())
    aquaTap.putWaterIn(cleaner)
    addItemTo(aquaTap)

    isWaterClean(aquaTap)
    isWaterClean(aquarium)

    aquarium.hasWaterSupplyOfType<TapWater>()
//    aquarium.waterSupply.isOfType<LakeWater>()
    aquaTap.hasWaterSupplyOfType<TapWater>()
    lakeAquarium.hasWaterSupplyOfType<TapWater>()
}