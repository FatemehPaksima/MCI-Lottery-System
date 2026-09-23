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

homework2/
├── src/
│ └── ir/
│ └── java/
│ ├── MCIMain.java
│ ├── MCIMenu.java
│ ├── MCIUser.java
│ ├── MCILottery.java
│ ├── SaveTenFirstUsers.java
│ └── SaveThreePrizes.java
└── README.md

---

## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/FatemehPaksima/homework2.git
