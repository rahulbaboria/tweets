/*
 * Copyright 2020 GT_APP.
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
package flick2know.fieldassist.core.platform.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
/**
 * Que: Why custom view model factory?,
 * Although it is possible to inject a ViewModel
 * into an activity or fragment with Dagger
 *
 * Ans:
 * This is done for the following reasons:
 *
 * 1) Injecting a ViewModel class is only possible for ViewModels
 * that have a default (empty) constructor.
 * If the ViewModel constructor has parameters,
 * then you need to inject a factory class that
 * implements ViewModelProvider.Factory.
 *
 * 2) It seems to be a common pattern when using Dagger
 * with the ViewModel to create a Module to encapsulate
 * the ViewModel injection code.
 * This Module would bind the ViewModel classes used in
 * the app into a map. It would also provide the
 * ViewModel factory class, which in turn uses the
 * map to create the ViewModel classes.
 * refer this link for 2)
 * https://stackoverflow.com/questions/46278357/understanding-android-architecture-components-example-githubbrowsersample-viewm
 *
 * */
@Singleton
class ViewModelFactory
@Inject constructor(
  private val creators: Map<Class<out ViewModel>,
          @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unknown ViewModel class $modelClass")

        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
