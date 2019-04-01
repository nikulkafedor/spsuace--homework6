package ru.spsuace.homework6.state;

import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Потокобезопасный контейнер для вычислений. Контейнер создается с некторым дэфолтным значеним.
 * Далее значение инициализируется, вычисляется и отдается в потребителю. В каждом методе контейнер меняет состояние
 * и делает некоторое вычисление (которое передано ему в определенный метод)
 *
 * Последовательность переходов из состояния в состояние строго определена:
 * START -> INIT -> RUN -> FINISH
 * Из состояния FINISH можно перейти или в состояние INIT или в состояние CLOSE.
 * CLOSE - конченое состояние.
 *
 * Если какой-либо метод вызывается после перехода в состояние CLOSE он должен кинуть ошибку.
 * Если вызван метод, который не соответствует текущему состоянию - он ждет,
 * пока не состояние не станет подходящим для него (или ждет состояние CLOSE, чтобы кинуть ошибку)
 *
 *
 * Есть три варианта решения этой задачи.
 * 1) через методы wait and notify - 4 балла
 * 2) через Lock and Condition - 4 балла
 * 3) через операцию compareAndSet на Atomic классах - 8 баллов
 *
 * Вы можете написать все 3 реализации, но минимум - хотябы одна
 */
public class CalculateContainer<T> {

    private State state = State.START;

    private T result;

    public CalculateContainer(T result) {
        this.result = result;
    }

    /**
     * Инициализирует результат и переводит контейнер в состояние INIT (Возможно только из состояния START и FINISH)
     */
    public void init(UnaryOperator<T> initOperator) {

    }

    /**
     * Вычисляет результат и переводит контейнер в состояние RUN (Возможно только из состояния INIT)
     */
    public void run(BinaryOperator<T> runOperator, T value) {

    }


    /**
     * Передает результат потребителю и переводит контейнер в состояние FINISH (Возможно только из состояния RUN)
     */
    public void finish(Supplier<T> finishSupplier) {

    }


    /**
     * Закрывает контейнер и передает результат потребителю. Переводит контейнер в состояние CLOSE
     * (Возможно только из состояния FINISH)
     */
    public void close(Supplier<T> closeSupplier) {

    }


}
