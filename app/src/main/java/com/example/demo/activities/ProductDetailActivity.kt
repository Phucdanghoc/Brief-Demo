package com.example.demo.activities

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.R
import com.example.demo.adapters.ParentAdapter
import com.example.demo.databinding.ActivityProductDetailBinding
import com.example.demo.events.OnChildItemClickListener
import com.example.demo.models.MenuItem
import com.example.demo.models.Product
import com.example.demo.utils.VideoPlayer
import com.example.demo.viewmodel.ProductViewModel

class ProductDetailActivity : AppCompatActivity(), OnChildItemClickListener {

    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var parentAdapter: ParentAdapter
    private lateinit var videoPlayer: VideoPlayer
    private lateinit var viewModel: ProductViewModel
    private lateinit var productCurrent: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        videoPlayer = VideoPlayer(this, binding.videoView)
        val productId = intent.getStringExtra("PRODUCT_ID") ?: "1"
        viewModel.getProductById(productId.toInt())
        viewModel.selectedProduct.observe(this) { product ->
            product?.let {
                updateUI(it)
                productCurrent = it
                setupRecyclerView()
            }
        }
        binding.back.setOnClickListener {
            finish()
        }
        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.anim_fadein)
        val animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.anim_fadeout)
        binding.menuRecyclerView.startAnimation(animationFadeIn)
        binding.layoutRight.startAnimation(animationFadeOut)
    }

    private fun updateUI(product: Product) {
        binding.tvTitle.text = product.title
        binding.tvDescription.text = product.description
        VideoPlayer(this, binding.videoViewBg).playVideo(product.productBackground)
    }

    private fun setupRecyclerView() {
        val menuItems = listOf(
            MenuItem("Ingredient", productCurrent.getFormattedIngredients()),
            MenuItem("Technology", productCurrent.getFormattedTechnology()),
            MenuItem("UsesHighlights", productCurrent.getFormattedUsesHighlights()),
            MenuItem("Achievement", productCurrent.getFormattedAchievements())
        )

        parentAdapter = ParentAdapter(menuItems, this, this)
        binding.menuRecyclerView.apply {
            adapter = parentAdapter
            layoutManager = LinearLayoutManager(this@ProductDetailActivity)
        }
    }

    override fun onChildItemClick(videoUrl: String) {
        binding.cardDescription.visibility = View.GONE
        binding.videoView.visibility = View.VISIBLE
        videoPlayer.playVideo(videoUrl)
    }

    override fun onStop() {
        super.onStop()
        videoPlayer.release()
    }
}
