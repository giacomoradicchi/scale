# Cotoresize

Un'applicazione Java per ridimensionare immagini secondo un fattore di scala personalizzato.

```mermaid
classDiagram
    class Node {
        -state: State
        -parent: Node
        -action: Action
        -path_cost: Real
        -depth: Integer
    }

    class State {
        <<interface>>
    }

    Node "0..*" o-- "1" State: associated with
```

## Descrizione

Scale.jar è un'utility a riga di comando che permette di ridimensionare immagini applicando un fattore di scala arbitrario. L'immagine ridimensionata viene salvata con un nuovo nome specificato dall'utente.

## Requisiti

- **Java Runtime Environment (JRE)** versione 8 o superiore
  - Per verificare se Java è installato, esegui: `java -version`
  - Se non è installato, scaricalo da [java.com](https://www.java.com) o [OpenJDK](https://openjdk.org/)

## Sintassi
```bash
java -jar scale.jar <file_input> <fattore_scala> <file_output>
```

## Parametri
- `<file_input>`: percorso del file immagine da ridimensionare
- `<fattore_scala>`: numero decimale che rappresenta il fattore di scala (es. 0.5 per dimezzare, 2.0 per raddoppiare)
- `<file_output>`: nome del file di output per l'immagine ridimensionata

## Esempi d'uso
Ridurre un'immagine al 50% delle dimensioni originali:
```bash
java -jar scale.jar foto.png 0.5 foto_piccola.png
```
Ingrandire un'immagine al doppio delle dimensioni:
```bash
java -jar scale.jar immagine.png 2.0 immagine_grande.png
```
Ridimensionare del 75%:
```bash
java -jar scale.jar documento.png 0.75 documento_ridotto.png
```

## Formati supportati
L'applicazione supporta i seguenti formati immagine:

- PNG (.png)
- GIF (.gif)

## Note

- Il fattore di scala deve essere un numero positivo
- Valori inferiori a 1.0 riducono l'immagine
- Valori superiori a 1.0 ingrandiscono l'immagine
- Assicurati di avere i permessi di lettura per il file di input e di scrittura per la cartella di output



