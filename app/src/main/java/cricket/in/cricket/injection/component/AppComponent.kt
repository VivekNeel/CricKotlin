package cricket.`in`.cricket.injection.component

import android.app.Application
import cricket.`in`.cricket.CricketApplication
import cricket.`in`.cricket.injection.module.AppModule
import cricket.`in`.cricket.injection.module.CricNetModule
import cricket.`in`.cricket.injection.module.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by vivek on 24/06/17.
 */

@Component(modules = arrayOf(AppModule::class, AndroidInjectionModule::class,
        MainActivityModule::class , CricNetModule::class ))
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(into: CricketApplication)
}