package co.zsmb.example.cleanbuzz.di.activity

import co.zsmb.example.cleanbuzz.presentation.BuzzPresenter
import co.zsmb.example.cleanbuzz.di.application.ApplicationComponent
import dagger.Component

@PerActivity
@Component(
        dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(
                BuzzPresentationModule::class,
                BuzzDomainModule::class
        ))
interface ActivityComponent {

    fun makeBuzzPresenter(): BuzzPresenter

}
