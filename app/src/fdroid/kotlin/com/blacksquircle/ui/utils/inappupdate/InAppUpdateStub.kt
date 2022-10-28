/*
 * Copyright 2021 Squircle IDE contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.leafcolor.mathide.ui.utils.inappupdate

import android.app.Activity
import android.util.Log

class InAppUpdateStub : InAppUpdate {

    companion object {
        private const val TAG = "InAppUpdateStub"
    }

    override fun checkForUpdates(activity: Activity, onComplete: () -> Unit) {
        Log.d(TAG, "checkForUpdates")
    }

    override fun completeUpdate() {
        Log.d(TAG, "completeUpdate")
    }
}