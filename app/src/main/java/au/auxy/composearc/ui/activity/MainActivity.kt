package au.auxy.composearc.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import au.auxy.composearc.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
