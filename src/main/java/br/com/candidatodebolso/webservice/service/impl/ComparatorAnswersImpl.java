package br.com.candidatodebolso.webservice.service.impl;

import br.com.candidatodebolso.webservice.persistence.model.candidate.Candidate;
import br.com.candidatodebolso.webservice.persistence.model.candidate.CandidateAnswerQuestion;
import br.com.candidatodebolso.webservice.persistence.model.question.Question;
import br.com.candidatodebolso.webservice.service.ComparatorAnswers;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ComparatorAnswersImpl implements ComparatorAnswers {

    private List<CandidateAnswerQuestion> candidateAnswerQuestions;
    private Map<Question, List<CandidateAnswerQuestion>> map;
    private CrudService<CandidateAnswerQuestion> candidateAnswerQuestionCrudService;

    @Autowired
    public ComparatorAnswersImpl(CrudService<CandidateAnswerQuestion> candidateAnswerQuestionCrudService) {
        this.candidateAnswerQuestionCrudService = candidateAnswerQuestionCrudService;
        setCandidateAnswerQuestions(candidateAnswerQuestionCrudService);
        separateAnswersByQuestion();
    }

    @Override
    public Candidate compare(List<Question> questions) {
        Map<Candidate, Integer> candidatePoints = new HashMap<>();
        candidateAnswerQuestions.stream()
                .map(CandidateAnswerQuestion::getCandidate)
                .forEach(candidate -> candidatePoints.put(candidate, 0));
        for (Question question : questions) {
            List<CandidateAnswerQuestion> candidateAnswerQuestions = map.get(question).stream()
                    .filter(candidateAnswerQuestion -> candidateAnswerQuestion.getAnswer() == question.getAnswer())
                    .collect(Collectors.toList());
            for (CandidateAnswerQuestion candidateAnswerQuestion : candidateAnswerQuestions) {
                candidatePoints.put(candidateAnswerQuestion.getCandidate(), candidatePoints.get(candidateAnswerQuestion.getCandidate()) + 1);
            }
        }
        return getCandidateWithMorePoints(candidatePoints);
    }

    private Candidate getCandidateWithMorePoints(Map<Candidate, Integer> candidatePoints) {
        boolean first = true;
        Candidate candidate = null;
        int points = 0;
        for (Map.Entry<Candidate, Integer> entry : candidatePoints.entrySet()) {
            if (first) {
                candidate = entry.getKey();
                points = entry.getValue();
                first = false;
            } else if (entry.getValue() > points) {
                candidate = entry.getKey();
                points = entry.getValue();
            }
        }
        return candidate;
    }

    private void separateAnswersByQuestion() {
        map = new HashMap<>();
        List<Question> questions = candidateAnswerQuestions.stream().map(CandidateAnswerQuestion::getQuestion).collect(Collectors.toList());
        for (Question question : questions) {
            List<CandidateAnswerQuestion> candidateAnswerQuestions = this.candidateAnswerQuestions.stream().filter(candidateAnswerQuestion -> candidateAnswerQuestion.getQuestion() == question).collect(Collectors.toList());
            map.put(question, candidateAnswerQuestions);
        }
    }

    private void setCandidateAnswerQuestions(CrudService<CandidateAnswerQuestion> candidateAnswerQuestionCrudService) {
        candidateAnswerQuestions = new ArrayList<>();
        candidateAnswerQuestionCrudService.listAll().forEach(candidateAnswerQuestion -> candidateAnswerQuestions.add(candidateAnswerQuestion));
    }

    @Override
    public void forceUpdate() {
        setCandidateAnswerQuestions(candidateAnswerQuestionCrudService);
        separateAnswersByQuestion();
    }
}
