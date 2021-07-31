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
package flick2know.fieldassist.core.annotation

import androidx.work.Worker
import dagger.MapKey
import javax.inject.Qualifier
import javax.inject.Scope
import kotlin.reflect.KClass

/**
 * @Scope main job is to ensure a variable is only created once and could be reused within a given scope
 * Here we have created a @scope by the name of [PerActivity]
 * refer this link for more on @scope [https://medium.com/@elye.project/dagger-2-for-dummies-in-kotlin-scope-d51a6b6e077f]
 * */
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerFragment

@Qualifier
annotation class ApplicationContext

