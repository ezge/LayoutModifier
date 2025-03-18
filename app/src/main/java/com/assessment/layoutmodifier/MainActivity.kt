package com.assessment.layoutmodifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assessment.layoutmodifier.ui.theme.LayoutModifierTheme
import androidx.compose.foundation.background
import kotlin.math.roundToInt

/*

Custom layouts are quite straightforward to implement and
fall into two categories. First category, a custom
layout can be implemented as a layout modifier which can
be applied to a single user interface element. Alternatively, a
new Layout composable can be written which applies to all
the children of a composable (the technique used by the
Box, Column, and Row composable).
In the rest of this chapter, we will explore

Custom layout modifiers are written using the syntax:
fun Modifier.<custom layout name> (// Optional parameters here) =
    layout { measurable, constraints ->
                // Code to adjust position and size of element }

The measurable parameter is the child element on which the
modifier was called, while the constraints parameter
contains the maximum and minimum width and height
values allowed for the child.
Specifying a new x and y position for the child relative to the
default position assigned to it by the parent. The modifier will
calculate the new position relative to 0, 0, and return
the new offset coordinates. The parent will then apply
the offset to the actual coordinates to move the child
to the custom position. a child must only be measured once
each time the modifier is called.

fun Modifier.exampleLayout(x: Int, y: Int) =
    layout { measurable, constraints ->
                // placeable instance contains height and width values
                val placeable = measurable.measure(constraints)
                layout(placeable.width, placeable.height) {
                        // positioning the child element
                        placeable.placeRelative(x, y)
                }
    }

*/

// the child element positioning based on any horizontal or vertical alignment line
// (or a combination of both)
fun Modifier.exampleLayout(fraction: Float) =
    layout { measurable, constraints ->
        // placeable instance contains height and width values
        val placeable = measurable.measure(constraints)
        /*
           vertical alignment line
           the modifier now accepts a floating-point parameter
           representing the position of the vertical alignment line
           as a percentage of the width of the child.
         */
        val x = -(placeable.width * fraction).roundToInt()
        layout(placeable.width, placeable.height) {
            // positioning the child element
            placeable.placeRelative(x = x, y = 0)
        }
    }


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LayoutModifierTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(120.dp, 80.dp)) {
        ColorBox(
            Modifier
                .exampleLayout(90, 50)
                .background(Color.Blue)
        )
    }
}

@Composable
fun ColorBox(modifier: Modifier = Modifier) {
    Box(Modifier.padding(1.dp).size(width = 50.dp, height = 10.dp).then(modifier))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LayoutModifierTheme {
        MainScreen()
    }
}