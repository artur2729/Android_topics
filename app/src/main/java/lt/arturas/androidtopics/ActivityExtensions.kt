package lt.arturas.androidtopics

import android.app.Activity
import android.content.Intent
import android.os.Build
import lt.arturas.androidtopics.Repository.Item

fun Activity.getExtraFromParcelable(resul: Intent?, keyName: String): Item? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        resul?.getParcelableExtra(
            keyName,
            Item::class.java
        )
    } else {
        resul?.getParcelableExtra(keyName)
    }
