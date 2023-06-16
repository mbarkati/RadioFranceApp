# RadioFranceApp

RadioFranceApp is a two-screen application that allows users to browse available brands and view the shows associated with each brand.

## Screens
![Brands Screen](https://github.com/mbarkati/RadioFranceApp/assets/65324644/5c4a8352-396a-46ff-8559-bec842ac1f33)  ![Shows Screen](https://github.com/mbarkati/RadioFranceApp/assets/65324644/88a67bdb-5ad9-4851-982d-13003cfd0f63)
### Brand Screen
The first screen displays a list of available brands. Users can tap on a brand to navigate to the second screen.



### Shows Screen
The second screen shows the shows associated with the selected brand. Users can view details of each show, such as title, description, and release date.



## Libraries Used

- **Apollo3:** We use Apollo3 library for handling GraphQL API requests and responses, enabling efficient and flexible data retrieval.

- **Jetpack Compose:** The UI of the application is built using Jetpack Compose, a modern declarative UI toolkit for building native Android applications.

- **Flow:** We utilize the Flow library to handle asynchronous data streams, enabling reactive programming and efficient data updates.

- **Coroutines:** Coroutines are used for handling asynchronous tasks, providing a concise and efficient way to write asynchronous code.

- **Clean Architecture:** The application follows the principles of clean architecture, which promotes separation of concerns and maintainability. It includes components such as repositories, use cases, and MVVM (Model-View-ViewModel) architecture.

- **Hilt:** Hilt is used for dependency injection in the application, providing a convenient and structured way to manage and provide dependencies.

## Installation

To run the application locally, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the project on an emulator or physical device.
