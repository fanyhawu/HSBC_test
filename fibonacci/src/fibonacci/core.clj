(ns fibonacci.core)

(defn add_big_num [num_str1 num_str2]
  (def num1 (atom num_str1))
  (def num2 (atom num_str2))

  (if (< (count num_str1) (count num_str2))
    (loop [i (- (count num_str2) (count num_str1))] ( when (> i 0)
                                                            (reset! num1 (str "0" @num1))
                                                            (recur (- i 1))
                                                            ))
    (loop [i (- (count num_str1) (count num_str2))] ( when (> i 0)
                                                            (reset! num2 (str "0" @num2))
                                                            (recur (- i 1))
                                                            ))
    )

  (def len (count @num1))
  (def flag (atom 0))
  (let [result_s (atom "")]
    (loop [i (- len 1)] ( when (>= i 0)
                          (def tmp (+ (- (int (get @num1 i)) (int \0)) (- (int (get @num2 i)) (int \0)) @flag ) )
                          (reset! flag (int (/ tmp 10)))
                          (reset! result_s (str (str (rem tmp 10)) @result_s))
                          (recur (- i 1))
                          )
                        )
    (if (= @flag 1)
      (reset! result_s (str \1 @result_s))
      ()
      )

    @result_s

    )



  )

(defn find_n_digit_fibonacci [n]
  (def a (atom "1"))
  (def b (atom "1"))

  (loop [i 1000]( when (< (count @b) n)
                           (def tmp_c (add_big_num @a @b))
                           (def tmp_b @b)
                           (reset! a tmp_b)
                           (reset! b tmp_c)
                           (recur (- i 1))
                           )
                )
  @b
  )


(println (find_n_digit_fibonacci 1000))


