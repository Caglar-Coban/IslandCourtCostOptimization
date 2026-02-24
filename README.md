# Islander Court Cost Simulation ⚖️

A Java Swing-based GUI application built to solve the "Islander Court" algorithmic case study. The project calculates the most cost-effective bribery strategy to gain freedom after being wrongfully accused of a crime on a fictional island.

## 📖 The Story & Case Rules

You are on vacation on an island. While wandering in the forest, you find a man with a knife in his chest. Just as you pull the knife out, the island police arrest you. The island's justice system is unique:

* There are **5 different judges**. The assigned judge is revealed at the last minute.
* The **number of jurors** is also revealed right before the trial.
* **Verdict Rules:**
  1. If the judge votes **"Acquit"**, you are completely free.
  2. If the judge **"Abstains"**, you need more than **50%** of the jury to vote for acquittal.
  3. If the judge votes **"Guilty"**, you need **100%** of the jury to vote for acquittal.

## 🎯 Goal
Calculate the absolute **minimum total cost** to guarantee your freedom based on the bribes demanded by the randomly assigned judge and the individual jurors.

## ✨ Features

* **Interactive Swing GUI:** Aesthetic and user-friendly custom interfaces with hover effects, custom borders, and transparent panels.
* **Dynamic Calculations:** Instantly computes 3 different scenarios based on user-defined inputs.
* **Optimal Strategy Recommendation:** Analyzes the math behind the scenes and visually highlights the cheapest path to freedom.

## 💻 Tech Stack
* **Language:** Java
* **UI Framework:** Java Swing & AWT

## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Caglar-Coban/IslandCourtCostOptimization.git](https://github.com/Caglar-Coban/IslandCourtCostOptimization.git)

## 📥 Download & Play (No IDE Required)

If you just want to run the simulation without looking at the code, you can download the executable JAR file!

1. Go to the [Releases](../../releases) page on the right sidebar.
2. Download the latest `.jar` file (e.g., `IslandCourtCostOptimizer.jar`).
3. Ensure you have Java installed on your computer.
4. Double-click the `.jar` file, or run the following command in your terminal:
   ```bash
   java -jar IslandCourtCostOptimizer.jar
