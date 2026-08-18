# Scientific Calculator — Core Java & Swing

A standalone scientific calculator application developed using **Core Java and Java Swing**.

The project focuses on implementing the calculator's core processing logic using fundamental Java concepts while using Swing only for the graphical user interface.

---

## Features

- Basic arithmetic operations
- Addition
- Subtraction
- Multiplication
- Division
- Decimal number support
- Multi-digit number input
- Operator handling
- Expression evaluation
- Clear/reset functionality
- Graphical user interface
- Error handling for invalid operations

---

## Technologies Used

- Java 17
- Core Java
- Java Swing
- AWT event handling
- STS / Eclipse

No external frameworks or libraries are required.

---

## Application Architecture

The application is divided into three main classes:

```text
                 CalculatorApp
                      |
                      v
               CalculatorFrame
                      |
              User Interaction
                      |
                      v
              CalculatorEngine
                      |
               Core Processing
                      |
                      v
                   Result
                      |
                      v
              CalculatorFrame
```

### `CalculatorApp`

Acts as the entry point of the application.

It starts the calculator application and creates the user interface.

### `CalculatorFrame`

Responsible for the graphical user interface.

It handles:

- Window creation
- Buttons
- Display field
- User input
- Button events
- Sending input to the calculator logic
- Displaying the result

### `CalculatorEngine`

Contains the core calculation logic.

It is responsible for processing the mathematical input and performing the required operations.

Keeping the calculation logic separate from the graphical interface makes the application easier to understand and maintain.

---

# Core Java Concepts Used

The calculator demonstrates several fundamental Java concepts.

## Object-Oriented Programming

The application is divided into classes based on responsibility.

```text
CalculatorApp
      |
      +---- starts application

CalculatorFrame
      |
      +---- handles user interface

CalculatorEngine
      |
      +---- handles calculation logic
```

This separation follows the basic principle of giving each class a specific responsibility.

---

## Strings and Characters

User input is processed as text before being interpreted as numbers and operators.

Characters can be examined individually to distinguish between:

```text
Digits       → 0 - 9
Decimal      → .
Operators    → + - * /
```

This allows the calculator to process numbers containing multiple digits.

For example:

```text
125
```

is treated as a complete number rather than three separate numbers.

---

## Multi-Digit Number Processing

When the user enters:

```text
125
```

the calculator does not immediately treat each character as an independent operand.

The input characters are processed sequentially and digits are combined to form the complete number.

Conceptually:

```text
'1' → first digit
'2' → append to existing number
'5' → append to existing number

Result → 125
```

The same approach allows decimal values to be processed.

---

## Operator Processing

Operators are identified separately from numeric characters.

For example:

```text
25 + 30
```

can be conceptually divided into:

```text
Operand  → 25
Operator → +
Operand  → 30
```

The calculator engine then performs the corresponding arithmetic operation.

---

# Expression Processing

The calculator processes user input by identifying:

1. Numbers
2. Operators
3. The relationship between operands and operators

For example:

```text
25 + 30
```

is processed as:

```text
25 → operand
+  → operator
30 → operand
```

The appropriate arithmetic operation is then performed by the calculator engine.

---

# Error Handling

The application handles invalid mathematical operations and invalid input conditions rather than allowing the application to terminate unexpectedly.

Examples include:

```text
Division by zero
Invalid input
Invalid expression
```

Error conditions can be represented to the user through the graphical interface.

---

# Swing User Interface

Java Swing is used only for creating the desktop graphical interface.

The application uses standard Swing components such as:

```text
JFrame
JButton
JTextField
```

The UI is responsible for collecting user input and displaying results.

The actual mathematical processing is kept separate in `CalculatorEngine`.

---

# Event Handling

Calculator buttons generate user interaction events.

The interface receives these events and determines which operation the user requested.

Conceptually:

```text
User clicks button
        |
        v
Event generated
        |
        v
Event handler
        |
        v
Calculator logic
        |
        v
Result displayed
```

This allows the calculator to respond dynamically to button presses.

---

# Project Structure

```text
src
└── com
    └── soumyadip
        └── calculator
            ├── CalculatorApp.java
            ├── CalculatorEngine.java
            └── CalculatorFrame.java
```

---

# Separation of Responsibilities

The application deliberately separates the user interface from the calculation logic.

```text
CalculatorFrame
      |
      | User Interface
      |
      v
CalculatorEngine
      |
      | Calculation Logic
      |
      v
     Result
```

This means the calculation logic can be understood independently from the Swing interface.

---

# How to Run

1. Install Java 17 or a compatible JDK.
2. Import the project into STS/Eclipse as an existing Java project.
3. Make sure the JDK is configured correctly.
4. Open `CalculatorApp.java`.
5. Run `CalculatorApp` as a Java Application.
6. The calculator window will open.

No external dependencies are required.

---

# Key Concepts Demonstrated

This project demonstrates practical usage of:

- Core Java
- Classes and Objects
- Encapsulation
- Methods
- Conditional statements
- Loops
- Strings
- Characters
- Numeric parsing
- Arithmetic operations
- Exception handling
- Event-driven programming
- Java Swing
- AWT event handling
- Separation of UI and business logic

---

# Purpose

The purpose of this project is to demonstrate how a practical desktop application can be developed using fundamental Java programming concepts while keeping the user interface and application logic separated.

The project intentionally uses **Core Java and the required Swing components without relying on external frameworks**.
