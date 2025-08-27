✅ Todo App

A simple and modern Task Management App built with Kotlin, MVVM architecture, Room Database, and Coroutines.
This app helps you manage daily tasks with ease — add, update, delete, and mark tasks as completed with an intuitive UI.

✨ Features
📌 Add Tasks – Create new tasks with just a few taps.
📝 Update Tasks – Edit tasks anytime.
✅ Mark as Done – Mark tasks as completed and keep track of progress.
🗑 Swipe to Delete – Swipe right to quickly delete a task (with ViewModel + Room handling).
🔄 MVVM Architecture – Clean and maintainable code structure.
💾 Room Database – Local storage for offline-first experience.
⚡ Coroutines + ViewModelScope – Efficient background processing without memory leaks.


🛠 Tech Stack
Language: Kotlin
Architecture: MVVM (Model-View-ViewModel)
Database: Room Persistence Library
Asynchronous Tasks: Coroutines + Flow
UI: RecyclerView, ViewBinding
Other: ItemTouchHelper (for swipe-to-delete)

📸 Screenshots:

<img width="316" height="576" alt="Screenshot 2025-08-27 142356" src="https://github.com/user-attachments/assets/43b80dbf-531d-4f16-9ed1-907661923cf9" />
<img width="313" height="569" alt="Screenshot 2025-08-27 141449" src="https://github.com/user-attachments/assets/08ec7844-3ba0-4b2c-8e6e-8115670574f8" />
<img width="320" height="579" alt="Screenshot 2025-08-27 141508" src="https://github.com/user-attachments/assets/a7fd8753-fd75-44eb-bc9d-6e1cc80fa81e" />
<img width="317" height="574" alt="Screenshot 2025-08-27 141515" src="https://github.com/user-attachments/assets/8e809b3d-1c43-42d5-af55-d9683272264f" />


🚀 Getting Started
Prerequisites

Android Studio (latest stable version recommended)
Minimum SDK: 21 (Lollipop)
Gradle (comes with Android Studio)


Installation
Clone the repository:
git clone https://github.com/your-username/todo-app.git
Open the project in Android Studio.
Sync Gradle and build the project.
Run the app on an emulator or physical device.



📂 Project Structure
📦 TodoApp
 ┣ 📂 data
 ┃ ┣ 📂 dao          # Data Access Objects
 ┃ ┣ 📂 entity       # Room entities (Task.kt)
 ┃ ┗ 📂 repository   # Repository layer
 ┣ 📂 ui
 ┃ ┣ 📂 viewmodel    # ViewModels
 ┃ ┗ 📂 activities   # Activities & Fragments
 ┣ 📂 utils          # Utility classes/helpers
 ┗ MainActivity.kt


🤝 Contributing
Contributions are welcome! If you’d like to:
Fork the repo.
Create a new branch: git checkout -b feature/your-feature
Commit changes: git commit -m "Add new feature"
Push to branch: git push origin feature/your-feature
Open a Pull Request.


