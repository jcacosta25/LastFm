package com.example.kon3050.lastfm.di.modules

import com.example.kon3050.lastfm.di.scopes.ActivityScope
import com.example.kon3050.lastfm.ui.detail.DetailActivity
import com.example.kon3050.lastfm.ui.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

//@Module(subcomponents = arrayOf(ActivitySubComponent::class))
@Module
abstract class ActivityBindingModule {

//    @Binds
//    @IntoMap
//    @ActivityKey(BaseActivity::class)
//    abstract fun bindYourActivityInjectorFactory(builder: ActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>


//    @ContributesAndroidInjector(modules = arrayOf(NavigationModule::class))
//    internal abstract fun contributeBaseActivity(): BaseActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
//

    @ActivityScope
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    internal abstract fun contributeDetailActivity(): DetailActivity

}
