# 📱 MCI Lottery System

A Java-based lottery management system that simulates the **Hamrah-e Avval (MCI)** mobile lottery campaign. Permanent MCI subscribers can register, participate in the lottery, and view results through a USSD-style console interface.

---

## ✨ Features

- 🎰 **USSD-Style Interface** — Simulates real MCI USSD codes (`*10*0#`, `*10*1#`, etc.)
- 👤 **User Registration & Login** — Register with phone number and username, or log in with existing credentials
- 🎁 **Random Lottery Draw** — Uses a 100-slot prize pool with only 3 winning slots
- 📞 **Prefix Validation** — Only permanent MCI subscribers with valid prefixes can participate
- 🔁 **Two-Attempt Rule** — Each user can participate twice; winning disables further attempts
- 💾 **Persistent User Data** — Registered users remain in the system after logout
- 🔙 **Back Navigation** — Entering `*0#` returns to the previous step

---

## 🎁 Prize Pool

| Slot | Prize |
|------|-------|
| 1 | 💰 50 Million Cash Prize |
| 2 | 💰 10 Million Cash Prize |
| 3 | 🌐 1-Year Unlimited Internet |

Only the first 3 slots of the 100-element prize array contain prizes; the rest are empty.

---

## 🛠️ Tech Stack

- **Language:** Java
- **Concepts:** OOP, Collections (ArrayList), Randomization, Input Validation
- **Interface:** Console-based (CLI)

---

## 📂 Project Structure

```
homework2/
└── src/
    └── ir/
        └── java/
            ├── MCIMain.java
            ├── MCIMenu.java
            ├── MCIUser.java
            ├── MCILottery.java
            ├── SaveTenFirstUsers.java
            └── SaveThreePrizes.java
```
## 🚀 How to Run

1. Clone the repository:
```bash
git clone https://github.com/YOUR-USERNAME/MCI-Lottery-System.git
```

2. Navigate to the source folder:
```bash
cd MCI-Lottery-System/src
```

3. Compile the Java files:
```bash
javac ir/java/*.java
```

4. Run the main class:
```bash
java ir.java.MCIMain
```

---

## 🎮 How to Use

Once the program starts, you'll see the welcome screen:

```
Welcome to Hamrah-e Avval (MCI)
Enter *10*0# to register
Enter *10*1# to login
```

Then in the main menu:

| Code | Action |
|------|--------|
| `*10*355#` | Participate in MCI Lottery |
| `*10*350#` | Show Lottery Results |
| `*10*100#` | Log Out |
| `*0#` | Go Back to Previous Step |

---

## ✅ Valid MCI Prefixes

Only permanent MCI subscribers with the following prefixes can participate:

```
0910, 0911, 0912, 0913, 0914, 0915, 0916,
0917, 0918, 0919, 0991, 0992, 0993
```

---

## 📌 Rules

1. **10 users** are pre-registered in the system at startup.
2. Each user can participate **at most twice** in the lottery.
3. **Winning disables** further participation for that user.
4. User data **persists** across logout/login sessions.
5. The lottery uses a **100-element array** where only slots `0`, `1`, and `2` hold prizes.

---

## 📜 License

This project is developed as part of a university Java programming course.
Feel free to use it for learning purposes.

---

## 👨‍💻 Author

**Fatemeh Paksima**
University Java Programming Course — Term 2

