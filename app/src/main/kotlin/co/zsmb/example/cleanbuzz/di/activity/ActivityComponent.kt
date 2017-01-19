package co.zsmb.example.cleanbuzz.di.activity

import co.zsmb.example.cleanbuzz.di.application.ApplicationComponent
import co.zsmb.example.cleanbuzz.presentation.BuzzPresenter
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
