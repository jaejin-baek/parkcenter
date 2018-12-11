package com.apollo.android.cleanarchitecture.mockito

class InjectedClass {
    lateinit var dependentClass: DependentClass

    fun settingDependentObject(dependentClass: DependentClass) {
        this.dependentClass = dependentClass
    }

    fun usingDependentObject() {
        this.dependentClass.testFunction()
    }
}