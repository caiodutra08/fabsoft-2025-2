# Fábrica de Software 2025/1

Caio e gasparzinho

## Propostas de projeto

- Gerenciamento de treinos
  - Funcionalidade 1 Cadastrar dados do usuário
  - Funcionalidade 2 Cadastrar treinos
  - Funcionalidade 3 Gerar treino personalizado para usuário com AI
  - Funcionalidade 4 Automatizar modificações no treino de acordo com performance
  - Funcionalidade 6 Escolher tipo de treino
  - Funcionalidade 7 Consulta com profissionais da area

## Histórias de usuário

- Como usuário eu gostaria de escolher quantos dias na semana irei treinar e assim a IA irá gerar treinos.
- Como usuário eu gostaria de selecionar o plano do aplicativo
- Como usuário eu posso selecionar a dificuldade para cada treino efetuado
- Como usuário poderei avaliar treino
- Como usuário posso pedir dicas nutricionais

```mermaid
classDiagram
    Customer"*"-->"1"ExerciseDays
    Customer"*"-->"*"Exercise
    class Customer{
        -id: string
        -name: string
        -age: int
        -weight: int
        -height: int
        -gender: string
        +getId()string
        +setId(id: string)void
        +getName()string
        +setName(name: string)void
        +getAge()int
        +setAge(age: int)void
        +getWeight()int
        +setWeight(weight: int)void
        +getHeight()int
        +setHeight(height: int)void
        +getGender()string
        +setGender(gender: string)void
        +getCustomer(id: string)Customer
    }
    class ExerciseDays{
        <<enumeration>>
        THREE_DAYS
        FOUR_DAYS
        FIVE_DAYS
    }
    class Exercise{
        -id: string
        -name: string
        -video: string
        -thubmnail: string
        +getId()string
        +setId(id: string)void
        +getName()string
        +setName(name: string)void
        +getVideo()string
        +setVideo(video: string)void
        +getThumbnail()string
        +setThumbnail(Thumbnail: string)void
        +getExercise(id: string)Exercise
    }
    class ExerciseExecuted{
        -id: string
        -duration: int
        -rating: int
        +getId()string
        +setId(id: string)void
        +getDuration()int
        +setDuration(id: string)void
        +getRating()int
        +setRating(id: string)void
    }
    ExerciseExecuted "*" --> "*" Customer
    ExerciseExecuted "*" --> "1" ExerciseDifficulty
    class ExerciseDifficulty{
        <<enumeration>>
        ONE
        TWO
        THREE
        FOUR
        FIVE
    }
```
