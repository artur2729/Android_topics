package lt.arturas.androidtopics

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Item(
    private val _id: Int,
    private var _text01: String,
    private var _text02: String,
    private var _creationDate: LocalDateTime = LocalDateTime.now(),
    private var _updateDate: LocalDateTime = LocalDateTime.now()
): Parcelable {

    @IgnoredOnParcel
    var id = this._id
    private set


    @IgnoredOnParcel
    var text01: String = ""
        get():String {
            return _text01
        }
        set(value) {
            field = value
            this._text01 = value
            this._updateDate = LocalDateTime.now()
        }

    @IgnoredOnParcel
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

    @IgnoredOnParcel
    var creationDate = this._creationDate
    private set

    //var updateDate = this._updateDate
    //private set

    @IgnoredOnParcel
    var updateDate: LocalDateTime
    get() = this._updateDate
    private set (value){
        this._updateDate = value
    }
}