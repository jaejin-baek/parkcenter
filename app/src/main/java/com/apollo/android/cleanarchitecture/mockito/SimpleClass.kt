package com.apollo.android.cleanarchitecture.mockito

class SimpleClass(val injectedClass: InjectedClass) {
    fun usingFunction() {
        injectedClass.usingDependentObject()
    }

    fun settingFunction() {
        injectedClass.settingDependentObject(DependentClass())
    }

    fun settingFunction2(dependentClass: DependentClass) {
        injectedClass.settingDependentObject(dependentClass)
    }
}