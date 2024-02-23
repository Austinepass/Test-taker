package com.orgustine.testtaker.data.repository

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import com.orgustine.testtaker.data.remote.QuizResponse
import com.orgustine.testtaker.domain.repository.QuizRepository
import com.orgustine.testtaker.util.Resource
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val generativeModel: GenerativeModel,
    private val moshiAdapter: JsonAdapter<QuizResponse>
) : QuizRepository {

    override suspend fun getQuestions(prompt: String): Resource<QuizResponse> {
        return withContext(Dispatchers.IO) {
            val response = generativeModel.generateContent(prompt).text
            parseJson(response)
        }
    }

    private fun parseJson(jsonString: String?): Resource<QuizResponse> {
        if (jsonString == null) return Resource.Error("Invalid Json")

        return try {
            val newString = jsonString
                .replace("```json", "").replace("```", "")
            Resource.Success(moshiAdapter.fromJson(newString))

        } catch (e: Exception) {
            e.printStackTrace()
            if (e is JSONException) {
                Resource.Error("Json Error", e)
            }
            Resource.Error(e.message!!)
        }

    }
}

val jsonString = """
    {
      "questions": [
        {
          "question": "What is the value of x in the equation 2x + 5 = 13?",
          "optionsList": [
            "2",
            "4",
            "5",
            "6"
          ],
          "answer": "4"
        },
        {
          "question": "What is the area of a circle with a radius of 5 cm?",
          "optionsList": [
            "25π cm²",
            "50π cm²",
            "75π cm²",
            "100π cm²"
          ],
          "answer": "25π cm²"
        },
        {
          "question": "What is the volume of a cube with a side length of 4 cm?",
          "optionsList": [
            "16 cm³",
            "24 cm³",
            "64 cm³",
            "96 cm³"
          ],
          "answer": "64 cm³"
        },
        {
          "question": "What is the probability of rolling a 6 on a standard six-sided die?",
          "optionsList": [
            "1/2",
            "1/3",
            "1/4",
            "1/6"
          ],
          "answer": "1/6"
        },
        {
          "question": "What is the slope of the line passing through the points (2, 3) and (4, 7)?",
          "optionsList": [
            "1",
            "2",
            "3",
            "4"
          ],
          "answer": "2"
        },
        {
          "question": "What is the value of the expression 3^3 - 2^4?",
          "optionsList": [
            "-1",
            "1",
            "5",
            "11"
          ],
          "answer": "-1"
        },
        {
          "question": "What is the value of the expression 3^3 - 2^4?",
          "optionsList": [
            "-1",
            "1",
            "5",
            "11"
          ],
          "answer": "-1"
        },
        {
          "question": "What is the value of the expression 3^3 - 2^4?",
          "optionsList": [
            "-1",
            "1",
            "5",
            "11"
          ],
          "answer": "-1"
        },
        {
          "question": "What is the value of the expression 3^3 - 2^4?",
          "optionsList": [
            "-1",
            "1",
            "5",
            "11"
          ],
          "answer": "-1"
        },
        {
          "question": "What is the area of a circle with a radius of 5 cm?",
          "optionsList": [
            "25π cm²",
            "50π cm²",
            "75π cm²",
            "100π cm²"
          ],
          "answer": "25π cm²"
        }
      ]
    }
""".trimIndent()