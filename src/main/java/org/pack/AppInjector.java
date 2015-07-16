package org.pack;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Module;

import java.util.Iterator;

public class AppInjector implements Module, Iterable<AbstractModule> {
    @Override
    public Iterator<AbstractModule> iterator() {
        return null;
    }

    @Override
    public void configure(Binder binder) {

    }
}
