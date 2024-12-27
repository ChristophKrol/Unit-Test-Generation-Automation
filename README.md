# Übersicht über den Automatisierungsprozess
## Python-Skripte
- generateJavaFiles.py: Methoden zur Genererierung von Java-Dateien der Testklassen 
- generateTests.py: API-Calls zum LLM und Erstellung von Testmethoden im JSON-Format
- projectWorkload.py
- workload_v2.py
- workload.py: Kann ausgeführt werden, um einzelne Testklassen für Java-Dateien im inputClasses-Ordner zu erstellen

## Ordner
- inputClasses: Ordner zum Hinzufügen von Java-Klassen, für die Tests generiert werden sollen
- inputProjects: Ordner, in den Java-Projekte hinzugefügt werden, um für jedes Projekt Tests zu generieren
- jsonTests: Wird vom Skript generiert. Erfolgreich generierte JSONs werden dort gespeichert
- javaClasses: Wird vom Skript generiert. Dort werden für die Projekte/Klassen die Testklassen gespeichert
