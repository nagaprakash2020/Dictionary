# Dictionary
Project with Dagger2, ViewModels, Junit4 and espresso Testing

This project can help any one looking to set up a new project with clean architecture and test cases.

1. MVVM Architecture
2. Dagger2
3. Retrofit with coroutines support
4. LiveData with coroutines
5. Mockito for UnitTesting
6. Espresso (Working on few more test cases)

api folder has ApiService interface to make api calls using retrofit
dagger folder contains the Base Component along with Dagger modules for Network, ViewModels and Activites
model class has all the models
repository class is responsible for serving data to View Models
synonyms folder has the feature where users can search for a word from Urban dictionary.

A little bit about the architecture

Views:
1. Views(Activities, Fragments, Adapters etc) hold the Android related code like EditBoxes, Buttons , or any kinda of views.
2. ViewModelFactory is injected into views. Which is inturn usefull to get a reference of ViewModel
3. Views observe the data on ViewModels and update according to data changes.

ViewModels:
1. View models holds the data required by the views.
2. Actions like clicks, onChange etc are delegated to ViewModels from Views and correspoding business logics are handled in ViewModels.
3. Like asking data from repository layer
4. Data in viewModels are updated based on business logic or from the repository which are inturn observed by views

Repository
1. Repository servers data to ViewModel.
2. Repository can get data either from a local File Storage or a Database or from a Api Call. This is abstracted from viewModels.
3. Currently this project is makind use of apiservice class to get data from UrbanDictionay servers.

