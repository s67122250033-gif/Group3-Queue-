# 🚀 Queue Algorithm Design & Analysis: CPU Process Scheduling

โครงการวิเคราะห์และเปรียบเทียบประสิทธิภาพของอัลกอริทึม **CPU Process Scheduling** โดยการประยุกต์ใช้โครงสร้างข้อมูลแบบ **Queue** และ **Circular Queue** ในภาษา Java ซึ่งเป็นส่วนหนึ่งของงานปฏิบัติตระกูล Data Structures & Algorithms (กลุ่มที่ 4)

---

## 📌 วัตถุประสงค์ (Objectives)
1. วิเคราะห์และเลือกใช้โครงสร้างข้อมูลแบบ Queue ที่เหมาะสมกับปัญหา CPU Scheduling
2. ออกแบบและพัฒนา Algorithm โดยใช้หลักการ FIFO (First-In-First-Out)
3. เปรียบเทียบประสิทธิภาพระหว่าง **FCFS (First-Come, First-Served)** และ **Round Robin (RR)**
4. วิเคราะห์ Time Complexity, Space Complexity และทดสอบ Execution Time จริงด้วย Java

---

## 🛠️ โครงสร้างข้อมูลและอัลกอริทึม (Algorithms & Data Structures)

### 1. Algorithm A: First-Come, First-Served (FCFS)
* **Data Structure:** Standard FIFO Queue (`java.util.ArrayDeque`)
* **หลักการ:** ประมวลผล Process ตามลำดับการมาถึง โดยรันจนเสร็จสิ้น (Non-preemptive)
* **Time Complexity:** $\mathcal{O}(n)$
* **Space Complexity:** $\mathcal{O}(n)$

### 2. Algorithm B: Round Robin (RR - Time Quantum = 3)
* **Data Structure:** Circular Queue (จำลองการทำงานด้วย `java.util.ArrayDeque`)
* **หลักการ:** หมุนเวียนจัดสรรเวลา CPU ให้แต่ละ Process ครั้งละไม่เกิน 3 หน่วยเวลา ($TQ = 3$) หากงานยังไม่เสร็จจะ Enqueue กลับไปต่อท้ายคิว (Preemptive)
* **Time Complexity:** $\mathcal{O}\left(n \times \frac{\max(\text{Burst})}{TQ}\right)$
* **Space Complexity:** $\mathcal{O}(n)$

---

## 📊 ชุดข้อมูลทดสอบ (Case Study Input)

| Process ID | Arrival Time | Burst Time |
| :---: | :---: | :---: |
| **P1** | 0 | 8 |
| **P2** | 0 | 4 |
| **P3** | 0 | 9 |
| **P4** | 0 | 5 |

---

## 📁 โครงสร้างไฟล์ใน Repository (Project Structure)

```text
.
├── src/
│   └── CPUScheduler.java     # Source Code หลักภาษา Java (รองรับ Java 8+)
├── docs/
│   ├── Report.pdf            # รายงานฉบับเต็ม
│   └── Presentation.pdf      # สไลด์นำเสนอ
└── README.md                 # เอกสารอธิบายโครงการ
