package co.zsmb.example.cleanbuzz.di.buzz

import co.zsmb.example.cleanbuzz.di.application.ApplicationComponent
import co.zsmb.example.cleanbuzz.di.base.ActivityComponent
import co.zsmb.example.cleanbuzz.di.base.PerActivity
import co.zsmb.example.cleanbuzz.presentation.buzz.BuzzPresenter
import dagger.Component

@PerActivity
@Component(
        dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(
                BuzzPresentationModule::class,
                BuzzDomainModule::class
        ))
interface BuzzActivityComponent : ActivityComponent<BuzzPresenter>
