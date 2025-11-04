#!/usr/bin/env python3
"""
send_report.py
Sends an HTML report file as an email attachment.
Requires the following environment variables:
  SMTP_SERVER, SMTP_PORT, SMTP_USER, SMTP_PASS, EMAIL_TO (comma-separated)
Usage example in GH Actions: python send_report.py
"""

import os
import sys
import smtplib
from email.message import EmailMessage
from pathlib import Path

# CONFIG — file path to your report (change if your report path differs)
REPORT_PATHS = [
    "test-output/ExtentReport.html",
    "target/surefire-reports/index.html",
    "target/site/allure-maven-plugin/index.html"
]

def find_report():
    for p in REPORT_PATHS:
        rp = Path(p)
        if rp.is_file():
            return rp
    return None

def main():
    report_file = find_report()
    if not report_file:
        print("ERROR: No report file found in expected locations:", REPORT_PATHS)
        sys.exit(2)

    smtp_server = os.getenv("SMTP_SERVER")
    smtp_port = int(os.getenv("SMTP_PORT", "587"))
    smtp_user = os.getenv("SMTP_USER")
    smtp_pass = os.getenv("SMTP_PASS")
    email_to = os.getenv("EMAIL_TO")

    if not (smtp_server and smtp_user and smtp_pass and email_to):
        print("ERROR: Missing SMTP credentials or EMAIL_TO environment variable.")
        print("Required: SMTP_SERVER, SMTP_PORT (optional), SMTP_USER, SMTP_PASS, EMAIL_TO")
        sys.exit(3)

    subject = f"Automation Test Report — {report_file.name}"
    body = f"Attached test report: {report_file}\n\nThis message was sent by GitHub Actions."

    msg = EmailMessage()
    msg["From"] = smtp_user
    msg["To"] = email_to
    msg["Subject"] = subject
    msg.set_content(body)

    # Attach HTML as alternative + attachment
    html = report_file.read_bytes()
    msg.add_attachment(html, maintype="text", subtype="html", filename=report_file.name)

    try:
        with smtplib.SMTP(smtp_server, smtp_port) as s:
            s.ehlo()
            s.starttls()
            s.login(smtp_user, smtp_pass)
            s.send_message(msg)
        print("Report email sent successfully to:", email_to)
    except Exception as e:
        print("Failed to send email:", e)
        sys.exit(4)

if __name__ == "__main__":
    main()
