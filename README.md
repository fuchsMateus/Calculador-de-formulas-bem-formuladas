# Calculador-de-formulas-bem-formuladas
Determina o valor lógico de uma fbf para todas as possibilidades de valores das suas proposições.

Exemplo:

Input: `(A~B)&A~B`

Output:

```
A B
F|F|= V
F|V|= V
V|F|= V
V|V|= V
```
**REGRAS**
| Significado  |  Notação  |
|-------------------|:---------------:|
| **Variável Proposicional** |  A, B, C... |
|  Conectivo Lógico: **NÃO** | -A |
|  Conectivo Lógico: **E** |  A&B |
|  Conectivo Lógico: **OU** | A\|B
|  Conectivo Lógico: **Condicional** |  A~B |
|  Conectivo Lógico: **Bicondicional** |  A%B |
|   **Parênteses** |  ((B~C)&A)\|C|

Obs:  
-Use o máximo de parênteses possíveis para evitar ambiguidades  
-São permitidas no máximo 5 variáveis proposicionais (A, B, C, D, E)
