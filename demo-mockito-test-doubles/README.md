# SOFTENG 283 — Mockito lecture demo (2026)

This is the instructor's email example for the test doubles lecture. `Messenger` uses a `TemplateEngine` to prepare a message and a `MailServer` to send it to a `Client`. The collaborators are interfaces, so the example can show how Mockito stubs a returned value and verifies calls and arguments without sending an email.

Run `./mvnw test` on macOS/Linux, `.\mvnw.cmd test` in Windows PowerShell, or `mvnw.cmd test` in Windows Command Prompt. `MessengerTest` is a disabled demonstration scaffold; it is completed during the live session.

The student exercise is in the teaching workspace at `2026/labs/lab-03-mockito-test-doubles/` and uses a library loan scenario.
