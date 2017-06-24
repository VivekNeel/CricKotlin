package cricket.`in`.cricket.injection.module

import android.arch.lifecycle.ViewModelProvider
import cricket.`in`.cricket.injection.component.ViewModelSubComponent
import cricket.`in`.cricket.viewmodel.AppViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vivek on 24/06/17.
 */

@Module(subcomponents = arrayOf(ViewModelSubComponent::class))
class AppModule {

    @Singleton
    @Provides
    fun provideViewModelFactory(
            viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory {
        return AppViewModelFactory(viewModelSubComponent.build())
    }
}
