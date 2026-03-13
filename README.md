# SpursManager - NBA General Manager Application

**SpursManager** is a web-based management platform designed for the General Manager of the **San Antonio Spurs**, allowing for comprehensive roster management, city-to-city travel optimization, and player health/contract tracking. 
> Built as a university group project for **WIA1002 Data Structure** at Universiti Malaya.

## 🚀 Key Features

*   **Roster Management:** Recruit players from a free agent market (integrated via an external **NBA API**) or remove them from the active roster.
*   **Dynamic Searching:** Advanced cumulative filtering of players based on attributes like Age, Height, Weight, Position, and Performance Stats (Points, Rebounds, Assists, etc.).
*   **NBA Cities Journey:** A graph-based visualization of 10 NBA cities with automated calculation of the **shortest path** (8629 km) starting from San Antonio using Depth-First Search.
*   **Injury Reserve Management:** A dedicated system to move injured players to a reserve list and track their recovery using a **Stack**.
*   **Contract Extension Queue:** A **Priority Queue** system for negotiating player contracts, prioritizing players with higher points.
*   **Performance Ranking:** An automated ranking system that calculates a position-specific **composite score** for players using an **Insertion Sort** algorithm.

## 🛠️ Data Structures & Algorithms

The project emphasizes the practical application of core data structures:
*   **Stacks (LIFO):** Used for managing the Injury Reserve.
*   **Priority Queues:** Used for the Contract Extension Queue to ensure high-performers are prioritized.
*   **Graphs:** Implemented as an adjacency list to model NBA cities and bidirectional distances.
*   **Depth-First Search (DFS):** Used to traverse the city graph to find the optimal path to visit all cities.
*   **Insertion Sort:** Employed to rank player performance by their composite scores efficiently for the roster size.
*   **Advanced Searching:** Used for multi-attribute dynamic filtering.

## 📏 NBA Roster Regulations

The application enforces official-style regulations to maintain a valid team:
1.  **Team Size:** Minimum of 10 players and a maximum of 15.
2.  **Positional Requirements:** Each team must have at least 2 Guards, 2 Forwards, and 2 Centers.
3.  **Salary Cap:** The total team salary cannot exceed **$20,000**.
4.  **Minimum Salaries:** Players averaging >20 PPG must have a minimum salary of $3,000; all others have a minimum of $1,000.

## 💻 Tech Stack

*   **Frontend:** Angular (TypeScript-based framework).
*   **Backend:** Spring Boot (Java) using Controllers, Services, Repositories, and Entities.
*   **Database:** MySQL Workbench (using JPA/Hibernate for data mapping).
*   **API:** External NBA API integration for real-time player statistics.

## ⚙️ Installation & Setup

### Prerequisites
*   **Node.js** (v20.10.0).
*   **npm** (v10.2.3).
*   **Angular CLI** (v18.0.3).
*   **MySQL Workbench**.

### Backend Setup
1.  Clone the repository: `https://github.com/Wrynaft/spursmanager.git`.
2.  In **MySQL Workbench**, create a schema named `spursmanager`.
3.  Navigate to `src/main/resources/application.properties` and update the `spring.datasource.username` and `spring.datasource.password` to match your local MySQL credentials.
4.  Build and run the Spring Boot application using your IDE (e.g., VSCode, NetBeans) or Maven.

### Frontend Setup
1.  Navigate to the frontend project folder in your terminal.
2.  Run `npm install` to install dependencies.
3.  Run `ng serve` to start the development server.
4.  Open your browser to `http://localhost:4200/`.

## 👥 Contributors (Looping Hoopers)
- [@Wrynaft](https://github.com/Wrynaft)
- [@johnong04](https://github.com/johnong04)
- [@khoonlyn913](https://github.com/khoonlyn913)
- [@Choongmh](https://github.com/Choongmh)
- [@JuenKai530](https://github.com/JuenKai530)
```
