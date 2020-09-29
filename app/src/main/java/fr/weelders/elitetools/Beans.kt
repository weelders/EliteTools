package fr.weelders.elitetools

class UserInputException(message: String) : Exception(message)


//------------------------------------------------------------------------------------
// DataClass
//------------------------------------------------------------------------------------

data class Stations(

    val id: Int,
    val name: String,
    val system_id: Int,
    val updated_at: Long,
    val max_landing_pad_size: String,
    val distance_to_star: Int,
    val government_id: Int,
    val government: String,
    val allegiance_id: Int,
    val allegiance: String,
    val states: List<States>,
    val type_id: Int,
    val type: String,
    val has_blackmarket: Boolean,
    val has_market: Boolean,
    val has_refuel: Boolean,
    val has_repair: Boolean,
    val has_rearm: Boolean,
    val has_outfitting: Boolean,
    val has_shipyard: Boolean,
    val has_docking: Boolean,
    val has_commodities: Boolean,
    val import_commodities: List<String>,
    val export_commodities: List<String>,
    val prohibited_commodities: List<String>,
    val economies: List<String>,
    val shipyard_updated_at: Long,
    val outfitting_updated_at: Long,
    val market_updated_at: Long,
    val is_planetary: Boolean,
    val selling_ships: List<String>,
    val selling_modules: List<Int>,
    val settlement_size_id: String?,
    val settlement_size: String?,
    val settlement_security_id: String?,
    val settlement_security: String?,
    val body_id: Int,
    val controlling_minor_faction_id: Int,
    val ed_market_id: Long
)

data class ComplexeStations(val systemPops: SystemPopsDistance, val stations: List<Stations>)

//Temporary desactivated Lists TODO
data class SystemPops(

    val id: Int,
    val edsm_id: Int,
    val name: String,
    val x: Double,
    val y: Double,
    val z: Double,
    val population: Long,
    val is_populated: Boolean,
    val government_id: Int,
    val government: String?,
    val allegiance_id: Int,
    val allegiance: String?,
    //val states : List<States>,
    val security_id: Int,
    val security: String?,
    val primary_economy_id: Int,
    val primary_economy: String?,
    val power: String?,
    val power_state: String?,
    val power_state_id: Int?,
    val needs_permit: Boolean,
    val updated_at: Long,
    val simbad_ref: String,
    val controlling_minor_faction_id: Int,
    val controlling_minor_faction: String?,
    val reserve_type_id: Int,
    val reserve_type: String?,
    // val minor_faction_presences : List<Minor_faction_presences>,
    val ed_system_address: Long
)

data class States(

    val id: Int,
    val name: String
)

data class SystemPopsDistance(
    val systemPops: SystemPops,
    val distance: Double
)

data class GalnetNews (

    val status : String,
    val feed : Feed,
    val items : List<Items>
)

data class Items (

    val title : String,
    val pubDate : String,
    val link : String,
    val guid : String,
    val author : String,
    val thumbnail : String,
    val description : String,
    val content : String,
)

data class Feed (

    val url : String,
    val title : String,
    val link : String,
    val description : String,
)