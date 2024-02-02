package com.pellto.youtoy.video.domain.model;

public enum EncodedState {
  // PREPARING -> ENCODING -> ENCODED -> UPLOADED -> COMPLETE
  PREPARING, ENCODING, ENCODED, UPLOADED, COMPLETE, ERROR, DELETED, CANCELED
}
