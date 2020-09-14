package fr.weelders.elitetools

class UserInputException(message: String) : Exception(message)


//------------------------------------------------------------------------------------
// DataClass
//------------------------------------------------------------------------------------

data class SystemResponse(val docs: ArrayList<Docs>)
data class Docs(

    val _id: String,
    val id: Int,
    val name_lower: String,
    val reserve_type: String,
    val reserve_type_id: Int,
    val controlling_minor_faction: String,
    val controlling_minor_faction_id: Int,
    val simbad_ref: String,
    val updated_at: String,
    val needs_permit: Boolean,
    val power_state_id: Int,
    val power_state: String,
    val power: String,
    val primary_economy: String,
    val primary_economy_id: Int,
    val security: String,
    val security_id: Int,
    val allegiance: String,
    val allegiance_id: Int,
    val government: String,
    val government_id: Int,
    val is_populated: Boolean,
    val population: Int,
    val z: Double,
    val y: Double,
    val x: Double,
    val name: String,
    val edsm_id: Int,
    val __v: Int,
    val ed_system_address: Long
)