package devpaul.business.kidmath.model

class User(
    var userId: String? = null,
    var name: String,
    var lastname: String,
    var rol: String? = null,
    val email: String,
    val password: String) {

    override fun toString(): String {
        return "$name $lastname"
    }


}