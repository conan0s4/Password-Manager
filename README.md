<h1>Password Manager (Java Swing + SQLite)</h1>

<h2>Overview</h2>

<p>
This is a Java-based desktop Password Manager built using Swing for the graphical user interface and SQLite for local data persistence. 
The system provides a simple but functional password storage solution with a master key authentication layer, password generation utility, 
and basic password strength validation.
</p>

<p>
It is designed as a foundational project to demonstrate core concepts in:
</p>

<ul>
  <li>GUI application development (Java Swing)</li>
  <li>Database integration (SQLite via JDBC)</li>
  <li>Basic security principles (password rules and secure random generation)</li>
  <li>CRUD operations in a local database environment</li>
</ul>

<hr>

<h2>System Features</h2>

<h3>Authentication System</h3>
<ul>
  <li>Master key setup on first launch</li>
  <li>Master key validation on subsequent logins</li>
  <li>Prevents access to password manager without correct credentials</li>
</ul>

<h3>Password Management</h3>
<ul>
  <li>Add new credential entries (username, password, platform)</li>
  <li>View all saved credentials in a table format</li>
  <li>Delete entries using unique ID</li>
</ul>

<h3>Password Security Tools</h3>
<ul>
  <li>Secure password generator using SecureRandom</li>
  <li>Password rules enforcement:
    <ul>
      <li>Minimum 8 characters</li>
      <li>At least 1 uppercase letter</li>
      <li>At least 1 lowercase letter</li>
      <li>At least 1 digit</li>
      <li>At least 1 special character</li>
    </ul>
  </li>
</ul>

<h3>Database Features</h3>
<ul>
  <li>Automatic SQLite database creation</li>
  <li>Auto table initialization on startup</li>
  <li>Persistent storage for:
    <ul>
      <li>Master key</li>
      <li>User credentials</li>
    </ul>
  </li>
</ul>

<hr>

<h2>Project Structure</h2>

<pre>
password.manager.main

├── PasswordManagerMain.java
│   → Application entry point

├── LoginUI.java
│   → Handles master key login and setup

├── PasswordManagerUI.java
│   → Main dashboard (CRUD operations + UI logic)

├── PasswordGenerator.java
│   → Secure password generation utility

├── SecurePasswordRule.java
│   → Password strength validation logic

└── SQLITEDataBaseHandler.java
    → SQLite database handler (CRUD + authentication storage)
</pre>

<hr>

<h2>System Design</h2>

<h3>Architecture Style</h3>

<p>The system follows a simple layered but tightly coupled architecture:</p>

<ul>
  <li><b>Presentation Layer (UI)</b>: LoginUI, PasswordManagerUI</li>
  <li><b>Logic Layer</b>: PasswordGenerator, SecurePasswordRule</li>
  <li><b>Data Layer</b>: SQLITEDataBaseHandler</li>
</ul>

<h3>Design Notes</h3>
<ul>
  <li>Direct communication between UI and database layer</li>
  <li>Minimal abstraction between components</li>
  <li>Designed for learning and simplicity rather than scalability</li>
</ul>

<hr>

<h2>How the System Works</h2>

<h3>1. Application Startup</h3>
<p>
The program starts from PasswordManagerMain, which launches the login interface.
</p>

<h3>2. Master Key Authentication</h3>
<ul>
  <li>If no master key exists, the user is prompted to create one</li>
  <li>If master key exists, the user must enter it correctly to proceed</li>
</ul>

<h3>3. Access to Password Manager</h3>
<p>
Once authenticated, the main dashboard (PasswordManagerUI) is displayed.
</p>

<h3>4. Password Operations</h3>
<ul>
  <li>Add new credentials</li>
  <li>Generate secure passwords</li>
  <li>View saved entries from SQLite database</li>
  <li>Delete entries by ID</li>
</ul>

<hr>

<h2>Database Design</h2>

<h3>Table: master_key</h3>

<table>
  <tr>
    <th>Column</th>
    <th>Type</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>password</td>
    <td>TEXT</td>
    <td>Master authentication key</td>
  </tr>
</table>

<h3>Table: passwords</h3>

<table>
  <tr>
    <th>Column</th>
    <th>Type</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>id</td>
    <td>INTEGER</td>
    <td>Primary key (auto increment)</td>
  </tr>
  <tr>
    <td>user</td>
    <td>TEXT</td>
    <td>Username or email</td>
  </tr>
  <tr>
    <td>password</td>
    <td>TEXT</td>
    <td>Account password</td>
  </tr>
  <tr>
    <td>platform</td>
    <td>TEXT</td>
    <td>Service or platform name</td>
  </tr>
</table>

<hr>

<h2>Security Design</h2>

<h3>Implemented Concepts</h3>
<ul>
  <li>SecureRandom-based password generation</li>
  <li>Password strength validation rules</li>
  <li>Prepared statements to prevent SQL injection</li>
  <li>Local-only database storage (no network dependency)</li>
</ul>

<h3>Limitations</h3>
<ul>
  <li>Passwords are stored in plaintext</li>
  <li>No encryption layer implemented</li>
  <li>Master key is not hashed</li>
  <li>Single-user design only</li>
</ul>

<hr>

<h2>Technical Stack</h2>
<ul>
  <li>Java (JDK 8+)</li>
  <li>Java Swing</li>
  <li>SQLite</li>
  <li>JDBC</li>
</ul>

<hr>

<h2>Setup Instructions</h2>

<h3>Requirements</h3>
<ul>
  <li>Java JDK 8 or higher</li>
  <li>SQLite JDBC driver</li>
</ul>

<h3>Run</h3>

<pre>
javac password/manager/main/*.java
java password.manager.main.PasswordManagerMain
</pre>

<hr>

<h2>Future Improvements</h2>

<ul>
  <li>Encryption / decryption layer for stored passwords</li>
  <li>Master key hashing (SHA-256 or BCrypt)</li>
  <li>Improved UI design and responsiveness</li>
  <li>MVC architecture refactor (separation of concerns)</li>
  <li>Export/import password vault functionality</li>
  <li>Multi-user support system</li>
</ul>
