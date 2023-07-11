package vn.tofu.notehere.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import vn.tofu.notehere.R
import vn.tofu.notehere.databinding.ActivityMainBinding
import vn.tofu.notehere.recyclerview.NoteAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)



    }
}