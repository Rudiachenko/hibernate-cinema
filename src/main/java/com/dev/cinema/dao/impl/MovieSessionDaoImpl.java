package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.model.MovieSession;
import exceptions.DataProcessingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger logger = Logger.getLogger(MovieSessionDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            logger.info("Movie session inserted successfully. " + movieSession);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert movie session with movie "
                    + movieSession.toString(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MovieSession get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MovieSession.class, id);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query =
                    session.createQuery("FROM MovieSession WHERE movie_id = :movieId "
                    + "AND show_time BETWEEN :start AND :end", MovieSession.class);
            query.setParameter("movieId", movieId);
            query.setParameter("start", date.atTime(LocalTime.MIN));
            query.setParameter("end", date.atTime(LocalTime.MAX));
            return query.getResultList();
        }
    }
}
