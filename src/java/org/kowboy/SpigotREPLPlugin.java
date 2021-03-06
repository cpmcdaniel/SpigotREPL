package org.kowboy;

import org.bukkit.plugin.java.JavaPlugin;
import clojure.lang.IFn;
import clojure.java.api.Clojure;

public class SpigotREPLPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        ClassLoader previous = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader cl = getClassLoader();
            Thread.currentThread().setContextClassLoader(cl);

            IFn require = Clojure.var("clojure.core", "require");
            require.invoke(Clojure.read("org.kowboy.spigot-repl"));

            IFn enable = Clojure.var("org.kowboy.spigot-repl", "enable");
            enable.invoke(this);
        } finally {
            Thread.currentThread().setContextClassLoader(previous);
        }
    }

    @Override
    public void onDisable() {
        ClassLoader previous = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader cl = getClassLoader();
            Thread.currentThread().setContextClassLoader(cl);

            IFn disable = Clojure.var("org.kowboy.spigot-repl", "disable");
            disable.invoke(this);
        } finally {
            Thread.currentThread().setContextClassLoader(previous);
        }
    }

    public static void main(String[] args) {
        IFn plus = Clojure.var("clojure.core", "+");
        System.out.println(plus.invoke(1, 2));
    }
}
