package th.ac.kku.cis.final_readbook_application

class ToDo {
    companion object Factory {
        fun create(): ToDo = ToDo()
    }

    var name: String? = null
    var namebook : String? = null
    var title : String? = null
    var description : String? = null
    var comment : String? = null
    var id : String? = null

}