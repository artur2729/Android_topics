package lt.arturas.androidtopics

import java.time.LocalDateTime

data class Item(
    private val _id: Int,
    private var _text01: String,
    private var _text02: String,
    private var _creationDate: LocalDateTime = LocalDateTime.now(),
    private var _updateDate: LocalDateTime = LocalDateTime.now()
) {
    var id = this._id
    private set


    var text01: String = ""
        get():String {
            return _text01
        }
        set(value) {
            field = value
            this._text01 = value
            this._updateDate = LocalDateTime.now()
        }

    var text02: String
        get():String {
            return _text02
        }
        set(value) {
            //field = value                //recommended
            // this.text02 = this._text02    //can't do it like this
            this._text02 = value
            this._updateDate = LocalDateTime.now()
        }

    var creationDate = this._creationDate
    private set

    var updateDate = this._updateDate
    private set
}