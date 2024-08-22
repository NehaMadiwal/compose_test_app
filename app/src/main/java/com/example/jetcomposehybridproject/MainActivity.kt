package com.example.jetcomposehybridproject

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.jetcomposehybridproject.ui.theme.JetComposeHybridProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetComposeHybridProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize()
            .semantics {
                testTagsAsResourceId = true
            }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Accessibility Test",
                fontSize = 20.sp,
                modifier = Modifier.padding(12.dp)
            )

            Button(onClick = {
                Toast.makeText(context, "Compose button: clicked", Toast.LENGTH_LONG).show()
            },
                shape = CircleShape,
                enabled = true,
                modifier = Modifier
                    .semantics {
                        contentDescription = "Test"
                        // Ensure that the item is marked as clickable for accessibility services
                        onClick {
                            true
                        }
                    }
                    .testTag("btn_compose")) {
                Text(text = "Compose button")
            }

            AndroidView(
                factory = { context ->
                    // Inflate the XML layout containing the button
                    LayoutInflater.from(context).inflate(R.layout.layout_xml_button, null)
                },
                update = { view ->
                    // Access and configure the button
                    val button = view.findViewById<Button>(R.id.xml_button)
                    button.setOnClickListener {
                        Toast.makeText(context, "XML button: clicked", Toast.LENGTH_LONG).show()
                    }
                }
            )


        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetComposeHybridProjectTheme {
        HomeScreen()
    }
}