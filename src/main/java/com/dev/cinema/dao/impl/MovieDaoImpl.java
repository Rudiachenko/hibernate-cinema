package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.model.Movie;
import exceptions.DataProcessingException;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {
    private static final Logger logger = Logger.getLogger(MovieDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public MovieDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            logger.info("Movie inserted successfully." + movie);
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert movie " + movie.getTitle(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Movie get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Movie.class, id);
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Movie", Movie.class).getResultList();
        }
    }
}
