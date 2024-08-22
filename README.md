﻿# compose_test_app

This is a jetpack compose app created to test AccessibilityService from [AccessibilityTest app](https://github.com/NehaMadiwal/accessibility_test)

This app has a Compose Button representing clickable views in compose and an AndroidView inflated with `android.widget.Button` representing xml ui. 

<h2>Issue faced with Accessibility service </h2>

When AccessibilityService from [AccessibilityTest app](https://github.com/NehaMadiwal/accessibility_test) is **ON** and we click on "XML BUTTON" we receive `AccessibilityEvent.TYPE_VIEW_CLICKED` event in `onAccessibilityEvent()` but when "Compose button" is clicked, we don't receive any click event in `onAccessibilityEvent()`

